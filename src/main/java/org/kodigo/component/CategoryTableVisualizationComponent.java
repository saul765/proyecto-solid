package org.kodigo.component;

import org.kodigo.domain.Category;

import java.util.List;
import java.util.function.Function;

public class CategoryTableVisualizationComponent extends BaseTableVisualization {

    public CategoryTableVisualizationComponent(TableComponent tableComponent) {
        super(tableComponent);
    }

    @Override
    protected List<String> headers() {
        return List.of("ID", "Name", "Description");
    }

    @Override
    protected <T> Function<T, List<String>> rowMapper() {
        return item -> {
            Category category = (Category) item;
            return List.of(
                    category.getId().toString(),
                    category.getName(),
                    category.getDescription()
            );
        };
    }

    @Override
    public <T> void showData(List<T> data) {
        List<Category> categories = (List<Category>) data;
        showTable(categories);
    }
}
