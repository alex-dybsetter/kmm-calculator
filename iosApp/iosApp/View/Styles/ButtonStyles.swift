//
//  ButtonStyles.swift
//  iosApp
//
//  Created by Alex Blass on 3/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CalculatorButtonStyle: ButtonStyle {
    
    let bgColor: Color
    let textColor: Color
    let outlineColor: Color
    let fontSize: CGFloat
    
    func makeBody(configuration: Self.Configuration) -> some View {
        configuration.label
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .padding(.horizontal)
            .background(bgColor)
            .foregroundColor(textColor)
            .font(.system(size: fontSize))
            .border(outlineColor, width: 1)
    }
}
