//
//  UZModuleDemoSwift.swift
//  UZModule
//
//  Created by kenny on 15/1/14.
//  Copyright (c) 2015年 APICloud. All rights reserved.
//

import UIKit
import Foundation

@objc(UZModuleDemoSwift)
class UZModuleDemoSwift: UZModule, UIAlertViewDelegate {
    override init!(uzWebView webView: Any!) {
        super.init(uzWebView: webView);
        
    }
    
    override func dispose() {
        //do clean
    }
    
    @objc func jsmethod_showAlert(_ context:UZModuleMethodContext) {
        let param = context.param! as NSDictionary;
        let title = param.stringValue(forKey: "title", defaultValue: nil);
        let msg = param.stringValue(forKey: "msg", defaultValue: nil);
        let alert = UIAlertController(title: title, message: msg, preferredStyle: .alert);
        alert.addAction(UIAlertAction(title: "取消", style: .default, handler: { (action) in
            let ret: Dictionary<String, Int> = ["index":1];
            context.callback(withRet: ret, err: nil, delete: true);
        }));
        alert.addAction(UIAlertAction(title: "确定", style: .default, handler: { (action) in
            let ret: Dictionary<String, Int> = ["index":2];
            context.callback(withRet: ret, err: nil, delete: true);
        }));
        self.viewController.present(alert, animated: true, completion: nil);
    }
    
    @objc func jsmethod_sync_systemVersion(_ context:UZModuleMethodContext) -> String {
        return UIDevice.current.systemVersion;
    }
}
