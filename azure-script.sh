#!/bin/bash
set -e

# =========================================
# SCRIPT AZURE CLI
# PROJETO CLYVO PREDICT
# =========================================

RESOURCE_GROUP="rg-clyvo-predict"
LOCATION="canadacentral"
VM_NAME="vm-clyvo-predict"
ADMIN_USER="azureuser"

IMAGE="almalinux:almalinux-x86_64:9-gen2:9.5.202411260"

SIZE="Standard_D2s_v3"

echo "Criando Resource Group..."

az group create \
  --name $RESOURCE_GROUP \
  --location $LOCATION

echo "Aceitando termos da imagem..."

az vm image terms accept \
  --publisher almalinux \
  --offer almalinux-x86_64 \
  --plan 9-gen2

echo "Criando VM..."

az vm create \
  --resource-group $RESOURCE_GROUP \
  --name $VM_NAME \
  --image $IMAGE \
  --size $SIZE \
  --admin-username $ADMIN_USER \
  --generate-ssh-keys \
  --public-ip-sku Standard

echo "Abrindo porta SSH..."

az vm open-port \
  --resource-group $RESOURCE_GROUP \
  --name $VM_NAME \
  --port 22 \
  --priority 100

echo "Abrindo porta aplicação..."

az vm open-port \
  --resource-group $RESOURCE_GROUP \
  --name $VM_NAME \
  --port 8080 \
  --priority 110

echo "Instalando Docker e ferramentas..."

az vm run-command invoke \
  --resource-group $RESOURCE_GROUP \
  --name $VM_NAME \
  --command-id RunShellScript \
  --scripts "
    sudo dnf update -y

    sudo dnf install -y \
      dnf-utils \
      git \
      nano \
      curl

    sudo dnf config-manager --add-repo \
      https://download.docker.com/linux/centos/docker-ce.repo

    sudo dnf install -y \
      docker-ce \
      docker-ce-cli \
      containerd.io \
      docker-buildx-plugin \
      docker-compose-plugin

    sudo systemctl enable docker
    sudo systemctl start docker

    sudo usermod -aG docker $ADMIN_USER

    docker --version
    git --version
    nano --version
  "

echo "Pegando IP público..."

IP_PUBLICO=$(az vm show \
  --resource-group $RESOURCE_GROUP \
  --name $VM_NAME \
  --show-details \
  --query publicIps \
  --output tsv)

echo "=================================="
echo "VM criada com sucesso!"
echo "IP: $IP_PUBLICO"
echo ""
echo "Conecte usando:"
echo "ssh $ADMIN_USER@$IP_PUBLICO"
echo "=================================="
