package org.kodigo.component;

import java.util.List;
import java.util.function.Function;

public abstract class BaseTableVisualization {

    private final TableComponent tableComponent;

    protected abstract List<String> headers();

    protected abstract <T> Function<T, List<String>> rowMapper();

    public BaseTableVisualization(TableComponent tableComponent) {
        this.tableComponent = tableComponent;
    }

    protected <T> void showTable(List<T> data) {
        String dataTable = tableComponent.createTable(headers(), data, rowMapper());

        System.out.println(dataTable);
    }

    public abstract <T> void showData(List<T> data);


}
