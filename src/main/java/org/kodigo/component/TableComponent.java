package org.kodigo.component;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;
import java.util.function.Function;

import static java.util.Objects.isNull;

public class TableComponent {


    private static TableComponent instance;

    private TableComponent() {
    }

    public static TableComponent getInstance() {
        if (isNull(instance)) {
            instance = new TableComponent();
        }
        return instance;
    }

    public <T> String createTable(List<String> headers, List<T> data, Function<T, List<String>> rowMapper) {

        AsciiTable instance = new AsciiTable();
        instance.getContext().setGrid(A7_Grids.minusBarPlusEquals());

        instance.addRule();
        instance.addRow(headers);
        instance.addRule();

        data.forEach(item -> instance.addRow(rowMapper.apply(item)));
        instance.addRule();


        instance.setTextAlignment(TextAlignment.CENTER);

        return instance.render();

    }
}
