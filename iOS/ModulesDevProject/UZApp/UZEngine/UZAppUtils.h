//
//  UZAppUtils.h
//  UZEngine
//
//  Created by broad on 13-11-12.
//  Copyright (c) 2013年 APICloud. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UZAppUtils : NSObject

#pragma mark - Device info
+ (BOOL)isSimulator;
+ (BOOL)popoverSupported;
+ (NSString *)getUUID;

#pragma mark - Paths
+ (NSString *)filePathInBundle:(NSString *)fileName;
+ (NSString *)appLibraryPath;
+ (NSString *)filePathInLibrary:(NSString *)fileName;
+ (NSString *)appLibraryCachesPath;
+ (NSString *)appDocumentPath;
+ (NSString *)filePathInDocument:(NSString *)fileName;

#pragma mark - Color
+ (BOOL)isValidColor:(NSString *)colorStr;
+ (UIColor *)colorFromNSString:(NSString *)colorStr;

#pragma mark - Global value
+ (void)setGlobalValue:(id)value forKey:(NSString *)key;
+ (id)globalValueForKey:(NSString *)key;

@end
