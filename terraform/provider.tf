provider "google" {
  credentials = "${file("../credentials/account.json")}"
  project     = "employee-payroll"
  region      = "us-central1"
}