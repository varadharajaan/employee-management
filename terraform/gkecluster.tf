resource "google_container_cluster" "employee-cluster" {
  name                     = "employee-management"
  network                  = "employee"
  subnetwork               = "employee-subnet1"
  location                 = "us-central1-a" # creates 1 node per zone
  # We can't create a cluster with no node pool defined, but we want to only use
  # separately managed node pools. So we create the smallest possible default
  # node pool and immediately delete it.
  remove_default_node_pool = true
  initial_node_count       = 1

  network_policy {
    enabled = true
  }

  maintenance_policy {
    daily_maintenance_window {
      start_time = "03:00"
    }
  }
}