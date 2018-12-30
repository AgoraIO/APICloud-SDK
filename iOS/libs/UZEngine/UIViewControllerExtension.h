//
//  UIViewControllerExtension.h
//  UZEngine
//
//  Created by kenny on 16-5-30.
//  Copyright (c) 2016年 APICloud. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef enum {
    SlidBackTypeFullPage,   //整个页面都可以滑动返回
    SlidBackTypeScreenEdge  //页面的左边缘可以滑动返回
} SlidBackType;

@interface UIViewController ()

@property (nonatomic) BOOL slidBackEnabled;         //是否允许滑动返回
@property (nonatomic) SlidBackType slidBackType;    //滑动返回类型

@end


@protocol UIViewControllerRotation <NSObject>

- (void)willRotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration;
- (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation;
- (void)viewWillTransitionToSize:(CGSize)size withTransitionCoordinator:(id <UIViewControllerTransitionCoordinator>)coordinator;

@end
