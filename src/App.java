import javax.swing.*;

import functions.Functions;
import interfaces.OperacionBinaria;
import interfaces.OperacionAtomica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> operationComboBox;
    private JButton calculateButton;
    private JButton mapReduceButton;
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

        mapReduceButton = new JButton("Ver Ejemplo de Map y Reduce");

        // configurar el diseño
        setLayout(new GridLayout(6, 1));
        add(textField1);
        add(textField2);
        add(operationComboBox);
        add(calculateButton);
        add(resultLabel);
        add(mapReduceButton);

        // agregar el actionistener a los botones
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResultado();
            }
        });

        mapReduceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String input = "";

                try {
                    input = JOptionPane.showInputDialog("Ingrese los numeros del array separados por comas");
                } catch (Exception ex) {
                    System.out.println("Entrada no Valida");
                    
                    return;
                }

                if(!input.equals("")){
                    if(input.length()>0){
                        try {
                            //Capturar los numeros
                            String[] str_numbers = input.split(",");

                            //map de los numeros para convertirlos en double
                            double[] numeros = Arrays.stream(str_numbers)
                                    .mapToDouble(Double::parseDouble)
                                    .toArray();
    
                            // recoleccion de los numeros
                            List<Double> num_list = Arrays.stream(numeros)
                                    .boxed()
                                    .collect(Collectors.toList());

                            arrayMap(num_list);
                            arrayReduce(num_list);

                        } catch (Exception ex) {
                            System.out.println("Entrada no Valida");

                            return ;
                        }
                    }
                }{
                    System.out.println("Entrada no Valida");
                }
            }
        });

        // mostrar el formulario
        setVisible(true);
    }

    private void calcularResultado() {
         //instancia de las funciones
        Functions funciones = new Functions();

        /* FUNCIONES LAMBDA */

        //funciones de un solo parametro
        OperacionAtomica cuadrado = null;
        
        //funciones con 2 parametros
        OperacionBinaria funcion = null;

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

        //switch de la operacion
        switch (selectedOperationString) {
            case "Cuadrado":
                cuadrado =  (a) -> funciones.Cuadrado(a);
                break;
            case "Suma":
                funcion = (a,b) -> funciones.Suma(a, b);
                break;
            case "Resta":
                funcion = (a, b) -> funciones.Resta(a, b);
                break;
            case "Potencia":
                funcion = (a, b) -> funciones.Potencia(a, b);
                break;
            case "Raiz":
                funcion = (a, b) -> funciones.Raiz(a, b);
                break;
            case "Multiplicacion":
                funcion = (a, b) -> funciones.Multiplicacion(a, b);
                break;
            case "Division":
                funcion = (a, b) -> funciones.Divicion(a, b);
                break;
            default:
                System.out.println("Operación no definida");
        }

        if(funcion != null){
            double resultado = funcion.calcular(valor1, valor2);
            resultLabel.setText("Resultado: " + resultado);
            
            return;
        }
        
        if(cuadrado != null){
            double resultado = cuadrado.calcular(valor1);
            resultLabel.setText("Resultado: " + resultado);

            return;
        }

        resultLabel.setText("Resultado: NaN");
    }

    public void arrayMap(List<Double> numbers){
        System.out.println("-----------------------------MAPEO--------------------------------\n");

        System.out.print("Array Original: ");
        System.out.print(numbers + "\n");

        //realizar el reccorrido de la lista mediante map y multiplicarla por 2
        List<Double> doubledNumbers = numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.print("Array Mapeado x*2: ");
        System.out.print(doubledNumbers + "\n");

        System.out.println("------------------------------------------------------------------\n");
    }

    public void arrayReduce(List<Double> numbers){
        System.out.println("-----------------------------REDUCE--------------------------------\n");
        System.out.print("Array Original: ");
        System.out.print(numbers +"\n");
        // se usa reduce para sumar todos los numeros
        double sum = numbers.stream().reduce(0.0, (acc, n) -> acc + n);

        System.out.print("Resultado de la Suma: ");
        System.out.print(sum + "\n");

        System.out.println("---------------------------------------------------------------\n");
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
