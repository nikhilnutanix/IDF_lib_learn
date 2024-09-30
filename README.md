# IDF Database Association Management

## Problem Statement

This project consists of two scripts that interact with an IDF database to manage associations between categories and entities or policies.

### Scripts

1. **Create Associations**

   - **Executable Name:** `create_single_association`
   - **Options:**
     - `--category`: The name of the category in fqName format: `category_key/category_value`. This should be the value of a valid existing category.
     - `--kind`: The type of the entity or policy. This should also be valid.
     - `--kind_id`: The UUID of the entity or policy being associated with this category.

   - **Conditions:**
     1. The script should first validate the category fqName, get its UUID, and then create entries in the appropriate tables.
     2. This action of creating the association should be correct and valid, such that the data sync service picks up the entry and creates entries in the target tables.
     3. It should also be such that the categories v4 APIs display the association properly.
     4. The script should correct the presence of existing entries, avoiding duplicate entries.

2. **Remove Associations**

   - **Executable Name:** `remove_category_associations`
   - **Options:**
     - `--category`: The category in fqName format.
     - `--kind`: The type of the entity or policy.

   - **Conditions:**
     All conditions from the first task hold true here as well.

### Global Options

- `--host`: The host IP address of the PCVM. Default: `localhost`
- `--port`: The IDF port. Default: `2027`
