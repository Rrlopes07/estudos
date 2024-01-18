resource "azurerm_virtual_machine" "main" {
    name                  = "vm_main"
    location              = azurerm_resource_group.resource_group.location
    resource_group_name   = azurerm_resource_group.resource_group.name
    network_interface_ids = [azurerm_network_interface.interface.id]
    vm_size               = "Standard_DS1_v2"

    storage_image_reference {
        publisher = "Canonical"
        offer     = "UbuntuServer"
        sku       = "20.04-LTS"
        version   = latest
    }

    storage_os_disk {
        name              = "disco1"
        caching           = "ReadWrite"
        create_option     = "FromImage"
        managed_disk_type = "Standard_LRS"
    }

    os_profile {
        computer_name  = "vm-azure"
        admin_username = "admin"
        admin_password = "admin@123"
    }

    os_profile_linux_config {
        disable_password_authentication = false
    }
}
