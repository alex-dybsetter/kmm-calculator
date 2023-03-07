import SwiftUI
import shared

struct ContentView: View {
    @State private var expression: String = ""
    
    private var calculator: Calculator = Calculator()
    
    var body: some View {
        VStack(spacing: 0) {
            let onDigitClicked = { (digit: String) in
                expression += digit
            }
            
            let operationsButtons = getOperationButtons()
            let numberButtons = getNumberButtons(onClick: onDigitClicked)
            
            Text(expression)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .trailing)
                .padding(.horizontal)
                .font(.system(size: K.Styles.outputTextStyle.fontSize))
                .foregroundColor(K.Styles.outputTextStyle.textColor)
                .background(K.Styles.outputTextStyle.bgColor)
                .border(K.StyleColors.white, width: 1)
            
            HStack(spacing: 0) {
                Button("AC", action: {
                    expression = ""
                }).buttonStyle(K.Styles.outputModifierButtonStyle)
                
                Button("+/-", action: {
                    expression = calculator.numberSignChanged(expression: expression)
                }).buttonStyle(K.Styles.outputModifierButtonStyle)
                
                Button("%", action: {
                    expression = calculator.percentage(expression: expression)
                }).buttonStyle(K.Styles.outputModifierButtonStyle)
                
                operationsButtons[0].buttonStyle(K.Styles.operationButtonStyle)
            }
            
            ForEach (0..<3) { i in
                HStack(spacing: 0) {
                    let num = 7 - (i * 3)
                    
                    numberButtons[num].buttonStyle(K.Styles.numberButtonStyle)
                    numberButtons[num+1].buttonStyle(K.Styles.numberButtonStyle)
                    numberButtons[num+2].buttonStyle(K.Styles.numberButtonStyle)
                    
                    operationsButtons[i+1].buttonStyle(K.Styles.operationButtonStyle)
                }
            }
            HStack(spacing: 0) {
                Button("0", action: { onDigitClicked("0") }).buttonStyle(K.Styles.numberButtonStyle)
                HStack(spacing: 0) {
                    Button(".", action: {
                        onDigitClicked(".")
                    }).buttonStyle(K.Styles.numberButtonStyle)
                    
                    Button("=", action: {
                        calculator.calculate(expression: expression)
                    }).buttonStyle(K.Styles.operationButtonStyle)
                }
            }
        }
    }
    
    private func getOperationButtons() -> Array<some View> {
        let onOperationClicked = { (operand: String) in
            //            if (!expression.isEmpty) {
            //                if (expression.contains(" ")) {
            //                    expression = output.replacingOccurrences(
            //                        of: operation,
            //                        with: operand,
            //                        options: .literal,
            //                        range: nil
            //                    )
            //                } else {
            //                    expression += " " + operand + " "
            //                }
            //            }
        }
        
        return [
            Button(
                Calculator.Operation.divide.symbol,
                action: { onOperationClicked(Calculator.Operation.divide.symbol) }
            ),
            Button(
                Calculator.Operation.multiply.symbol,
                action: { onOperationClicked(Calculator.Operation.multiply.symbol) }
            ),
            Button(
                Calculator.Operation.add.symbol,
                action: { onOperationClicked(Calculator.Operation.add.symbol) }
            ),
            Button(
                Calculator.Operation.subtract.symbol,
                action: { onOperationClicked(Calculator.Operation.subtract.symbol) }
            )
        ]
    }
    
    private func getNumberButtons(onClick: @escaping (String) -> Void) -> Array<some View> {
        var buttons: [Button] = [Button("", action: {})]
        for i in 1...9 {
            buttons.append(
                Button("\(i)", action: { onClick("\(i)") })
            )
        }
        
        return buttons
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
