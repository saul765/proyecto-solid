package org.kodigo.component;

import org.kodigo.enums.TableType;

public class TableVisualizationFactory {


    public static BaseTableVisualization getTableVisualization(TableType tableType) {

        TableComponent tableComponent = TableComponent.getInstance();
        return switch (tableType) {
            case USER -> new UsersTableVisualizationComponent(tableComponent);
            case CATEGORY -> new CategoryTableVisualizationComponent(tableComponent);
            case PRODUCT -> new ProductsTableVisualizationComponent(tableComponent);
            case SHOPPING_CART -> new ShoppingCartVisualizationComponent(tableComponent);
        };
    }
}
