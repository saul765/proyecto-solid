package org.kodigo.component;

import org.kodigo.domain.User;

import java.util.List;
import java.util.function.Function;

public class UsersTableVisualizationComponent extends BaseTableVisualization {


    public UsersTableVisualizationComponent(TableComponent tableComponent) {
        super(tableComponent);
    }

    @Override
    protected List<String> headers() {
        return List.of("ID", "Name", "Email", "Address");
    }

    @Override
    protected <T> Function<T, List<String>> rowMapper() {
        return item -> {
            User user = (User) item;
            return List.of(
                    user.getId().toString(),
                    user.getName(),
                    user.getEmail(),
                    user.getAddress()
            );
        };
    }

    @Override
    public <T> void showData(List<T> data) {
        List<User> users = (List<User>) data;
        showTable(users);
    }
}
