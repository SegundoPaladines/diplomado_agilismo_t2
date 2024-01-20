import javax.swing.*;

import functions.Functions;
import interfaces.OperacionBinaria;
import interfaces.OperacionAtomica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> operationComboBox;
    private JButton calculateButton;
    private JLabel resultLabel;

    public App() {
        // configurar el marco
        setTitle("Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // crear componentes
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);

        operationComboBox = new JComboBox<>(new String[]{"Suma", "Resta" ,"Cuadrado", "Potencia", "Raiz", "Multiplicacion", "Division"});
        calculateButton = new JButton("Calcular");
        resultLabel = new JLabel("Resultado: ");

        // configurar el diseño
        setLayout(new GridLayout(5, 1));
        add(textField1);
        add(textField2);
        add(operationComboBox);
        add(calculateButton);
        add(resultLabel);

        // agregar el ActionListener al botón
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResultado();
            }
        });

        // mostrar el formulario
        setVisible(true);
    }

    private void calcularResultado() {
         //instancia de las funciones
        Functions funciones = new Functions();

        //funciones lamda

        //funciones de un solo parametro
        OperacionAtomica cuadrado = (a) -> funciones.Cuadrado(a);
        
        //funciones con 2 parametros
        OperacionBinaria potencia = (a, b) -> funciones.Potencia(a, b);
        OperacionBinaria raiz = (a, b) -> funciones.Raiz(a, b);
        OperacionBinaria multiplicacion = (a, b) -> funciones.Multiplicacion(a, b);
        OperacionBinaria division = (a, b) -> funciones.Divicion(a, b);
        OperacionBinaria suma = (a,b) -> funciones.Suma(a, b);
        OperacionBinaria resta = (a, b) -> funciones.Resta(a, b);

        // la opcion selecionada
        String selectedOperationString = (String) operationComboBox.getSelectedItem();

        try {
            if(selectedOperationString.equalsIgnoreCase("Cuadrado"))
            {
                Double.parseDouble(textField1.getText());
            }else{
                Double.parseDouble(textField1.getText());
                Double.parseDouble(textField2.getText());
            }
            
        } catch (Exception e) {
            resultLabel.setText("NaN");
            //mensaje de error
            return;
        }

        // valores de las entradas
        double valor1 = Double.parseDouble(textField1.getText());
        double valor2 = Double.parseDouble(textField2.getText());

        double resultado = 0;

        switch (selectedOperationString) {
            case "Cuadrado":
                resultado = cuadrado.calcular(valor1);
                break;
            case "Suma":
                resultado = suma.calcular(valor1, valor2);
                break;
            case "Resta":
                resultado = resta.calcular(valor1, valor2);
                break;
            case "Potencia":
                resultado = potencia.calcular(valor1, valor2);
                break;
            case "Raiz":
                resultado = raiz.calcular(valor1, valor2);
                break;
            case "Multiplicacion":
                resultado = multiplicacion.calcular(valor1, valor2);
                break;
            case "Division":
                resultado = division.calcular(valor1, valor2);
                break;
            default:
                System.out.println("Operación no definida");
        }

        // mostrar el resultado en el JLabel
        resultLabel.setText("Resultado: " + resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
