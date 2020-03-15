resource "google_container_node_pool" "employee-nodepool-01" {
  name       = "employee-nodepool-01"
  location   = "us-central1-a"
  cluster    = "${google_container_cluster.employee-cluster.name}"
  node_count = 3

  node_config {
    preemptible  = true
    machine_type = "n1-standard-2"

    metadata = {
      disable-legacy-endpoints = "true"
    }

    labels = {
      team = "devops"
    }

    oauth_scopes = [
      "https://www.googleapis.com/auth/devstorage.read_only",
      "https://www.googleapis.com/auth/logging.write",
      "https://www.googleapis.com/auth/monitoring",
    ]
  }

  management {
    auto_repair  = true
    auto_upgrade = true
  }

  autoscaling {
    min_node_count = 3
    max_node_count = 6
  }

}