package br.edu.ufersa.hospital.api.controller;

import java.util.function.BiConsumer;
//Fazer os import do JavaFX e das views quando implementar
public class Utils {

  // Método genérico para adicionar um botão dentro da coluna de uma TableColumn
  public static <T> void initButtons(TableColumn<T, T> tableColumn, int size, String svgIcon,
    String colorClassName, BiConsumer<T, ActionEvent> buttonAction) {
    final int COLUMN_ICON_SPACE = 20; // espaço extra que terá dentro da célula da tabela
    tableColumn.setMinWidth(size + COLUMN_ICON_SPACE);
    tableColumn.setMaxWidth(size + COLUMN_ICON_SPACE);
    tableColumn.setStyle("-fx-alignment: CENTER"); // centraliza os botões

    Callback<TableColumn<T, T>, TableCell<T, T>> cellFactory = new Callback<TableColumn<T, T>, TableCell<T, T>>() {
      @Override
      public TableCell<T, T> call(final TableColumn<T, T> param) {
        final TableCell<T, T> cell = new TableCell<T, T>() {
          // chama o método auxiliar para criar um botão com o ícone svg dentro
          private final Button btn = createIconButton(svgIcon, size, colorClassName);

          // inseri o botão dentro da célula
          @Override
          public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(btn);
            }
          }

        {
          // define o que irá acontecer quando o botão for clicado
          btn.setOnAction((ActionEvent event) -> {
            T data = getTableView().getItems().get(getIndex());
            buttonAction.accept(data, event);
          });
        }
      };
      return cell;
      }
    };
    tableColumn.setCellFactory(cellFactory);
  }

  // Cria um botão com o ícone svg dentro
  public static Button createIconButton(String imgPath, int size, String colorClassName) {
    SVGPath path = new SVGPath();
    path.setContent(imgPath);
    Bounds bounds = path.getBoundsInLocal();

    // scale to size size x size (max)
    double scaleFactor = size / Math.max(bounds.getWidth(), bounds.getHeight());
    path.setScaleX(scaleFactor);
    path.setScaleY(scaleFactor);
    path.getStyleClass().add(colorClassName); // define a cor do ícone

    Button button = new Button();
    button.setPickOnBounds(true); // Garente que o botão terá o fundo transparente
    button.setGraphic(path); // inseri o ícone gerado pelo svg no botão
    button.setAlignment(Pos.CENTER);
    button.getStyleClass().add("icon-button"); // classe criada para não ter o fundo cinza
    // necessários para o ícone ficar contido dentro do botão
    button.setMaxWidth(size);
    button.setMaxHeight(size);
    button.setMinWidth(size);
    button.setMinHeight(size);
    button.setPrefWidth(size);
    button.setPrefHeight(size);
    return button;
  }
}
