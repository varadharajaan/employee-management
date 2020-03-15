resource "google_compute_network" "employee-network" {
  name                    = "employee"
  auto_create_subnetworks = "false"
}

resource "google_compute_subnetwork" "employee-subnetwork-subnet1" {
  name                     = "employee-subnet1"
  ip_cidr_range            = "10.0.0.0/24"
  network                  = "${google_compute_network.employee-network.self_link}"
  region                   = "us-central1"
  private_ip_google_access = true
}

resource "google_compute_subnetwork" "employee-subnetwork-subnet2" {
  name                     = "employee-subnet2"
  ip_cidr_range            = "10.1.0.0/24"
  network                  = "${google_compute_network.employee-network.self_link}"
  region                   = "us-central1"
  private_ip_google_access = true
}