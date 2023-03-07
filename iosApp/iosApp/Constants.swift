//
//  Constants.swift
//  iosApp
//
//  Created by Alex Blass on 3/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct K {
    struct StyleColors {
        static let primary = Color("Primary")
        static let primaryVariant = Color("PrimaryVariant")
        static let accent = Color("Accent")
        static let text = Color("Font")
        static let gray = Color("Gray")
        static let white = Color.white
    }
    
    struct Styles {
        static let outputTextStyle = OutputTextStyle(
            bgColor: StyleColors.gray,
            textColor: StyleColors.text,
            fontSize: 50,
            borderSize: 1
        )
        
        //MARK: - Button Styles
        static let numberButtonStyle = CalculatorButtonStyle(
            bgColor: StyleColors.primary,
            textColor: StyleColors.text,
            outlineColor: StyleColors.white,
            fontSize: 50
        )
        
        static let operationButtonStyle = CalculatorButtonStyle(
            bgColor: StyleColors.accent,
            textColor: StyleColors.text,
            outlineColor: StyleColors.white,
            fontSize: 40
        )
        
        static let outputModifierButtonStyle = CalculatorButtonStyle(
            bgColor: StyleColors.primaryVariant,
            textColor: StyleColors.text,
            outlineColor: StyleColors.white,
            fontSize: 30
        )
    }
}
