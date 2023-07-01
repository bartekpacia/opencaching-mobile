import Foundation
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
  func makeUIViewController(context: Context) -> some UIViewController {
    return IOSAppKt.MainViewController()
  }
  
  func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
    
  }
}
