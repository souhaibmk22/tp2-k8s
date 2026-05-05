
---

## 🚀 Fonctionnement global

1. Un développeur modifie un fichier dans le dépôt (ex: `values.yaml` pour changer le nombre de réplicas).
2. Il `git push` sur GitHub.
3. ArgoCD, installé dans le cluster, détecte le changement (toutes les 3 minutes ou manuellement).
4. ArgoCD génère les manifests Kubernetes à partir du Helm chart et les applique automatiquement.
5. Le cluster Minikube ajuste son état pour correspondre exactement au contenu du dépôt.

---

## 🛠 Prérequis

- Cluster Kubernetes (Minikube multi-nœuds : `minikube start --nodes 3`)
- ArgoCD installé dans le cluster
- CLI `argocd` configuré
- Docker (images locales construites)

Les images Docker utilisées sont locales :
- `ms-patient-image`
- `ms-ordonnance-image`
- `ms-remboursement-image`

Elles doivent être chargées dans chaque nœud du cluster (voir section **Images locales**).

---

## 📥 Installation du Helm chart avec ArgoCD

### 1. Se connecter à ArgoCD

```bash
kubectl port-forward svc/argocd-server -n argocd 8080:443
# Récupérer le mot de passe admin
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
argocd login localhost:8080 --username admin --password <PASSWORD> --insecure
