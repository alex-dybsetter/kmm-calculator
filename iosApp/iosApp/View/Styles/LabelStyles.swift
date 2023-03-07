//
//  LabelStyles.swift
//  iosApp
//
//  Created by Alex Blass on 3/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

protocol TextStyle {
    var bgColor: Color { get }
    var textColor: Color { get }
    var fontSize: CGFloat { get }
    var borderSize: CGFloat { get }
}

struct OutputTextStyle: TextStyle {
    let bgColor: Color
    let textColor: Color
    let fontSize: CGFloat
    let borderSize: CGFloat
}
