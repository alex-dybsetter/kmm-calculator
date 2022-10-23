import SwiftUI
import shared

struct ContentView: View {
    private var calculator: Calculator = Calculator()
    
    @State private var operation: String = ""
    @State private var output: String = ""
    
    let onClick = { print("Button clicked") }

    var body: some View {
        let onDigitClicked = { (digit: String) in
            output += digit
        }
        
        let onOperationClicked = { (operand: String) in
            if (!output.isEmpty) {
                if (output.contains(" ")) {
                    output = output.replacingOccurrences(
                        of: operation,
                        with: operand,
                        options: .literal,
                        range: nil
                    )
                } else {
                    output += " " + operand + " "
                }
                
                    operation = operand
                }
            }
        
        let onEqualsClicked = {
                if (!operation.isEmpty) {
                    var parts = output.components(separatedBy: " ")
                    var num1 = parts[0]
                    var num2 = parts[2]
                    var op = parts[1]

                    
                    var result =
                    calculator.equals(
                        num1: Double(num1) ?? 0.0,
                        num2: Double(num2) ?? 0.0,
                        operationType: op
                    )

                    output = String(result)
                    operation = ""
                }
            }
        
        let onClearClicked = {
            output = ""
            operation = ""
        }
        
        VStack {
            Text(output)
            HStack {
                VStack {
                    HStack {
                        Button("7", action: { onDigitClicked("7") })
                        Button("8", action: { onDigitClicked("8") })
                        Button("9", action: { onDigitClicked("9") })
                    }
                    HStack {
                        Button("4", action: { onDigitClicked("4") })
                        Button("5", action: { onDigitClicked("5") })
                        Button("6", action: { onDigitClicked("6") })
                    }
                    HStack {
                        Button("1", action: { onDigitClicked("1") })
                        Button("2", action: { onDigitClicked("2") })
                        Button("3", action: { onDigitClicked("3") })
                    }
                    HStack {
                        Button("0", action: { onDigitClicked("0") })
                    }
                }
                VStack {
                    Button("+", action: { onOperationClicked("+") })
                    Button("-", action: { onOperationClicked("-") })
                    Button("x", action: { onOperationClicked("x") })
                    Button("/", action: { onOperationClicked("/") })
                    Button("=", action: onEqualsClicked)
                }
            }
            Button("CLEAR ALL", action: onClearClicked)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
