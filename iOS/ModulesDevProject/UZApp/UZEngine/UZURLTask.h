//
//  UZURLTask.h
//  UZEngine
//
//  Created by kenny on 16/12/20.
//  Copyright © 2016年 APICloud. All rights reserved.
//

#import <Foundation/Foundation.h>

@class UZURLRequest;
@class UZURLResponse;

typedef void (^UZURLTaskHeadersBlock)(NSDictionary *responseHeaders);
typedef void (^UZURLTaskProgressBlock)(int64_t bytes, int64_t total);
typedef void (^UZURLTaskCompletionBlock)(UZURLResponse *response);
typedef void (^UZURLTaskFailedBlock)(NSError *error);
typedef void (^UZURLTaskRedirectedBlock)(NSURL *redirectedURL);

@interface UZURLTask : NSObject

@property (readonly, copy) NSString *taskIdentifier;

+ (instancetype)taskWithRequest:(UZURLRequest *)request;

@property (nullable, readonly, strong) UZURLRequest *request;

@property (nullable, readonly, strong) UZURLResponse *response;

@property (nullable, copy) UZURLTaskHeadersBlock headersReceivedBlock;  // 收到响应头的回调
@property (nullable, copy) UZURLTaskCompletionBlock completionBlock;    // 请求成功的回调
@property (nullable, copy) UZURLTaskFailedBlock failedBlock;            // 请求失败的回调
@property (nullable, copy) UZURLTaskProgressBlock bytesSentBlock;       // 发送数据进度回调
@property (nullable, copy) UZURLTaskProgressBlock bytesReceivedBlock;   // 收到数据进度回调
@property (nullable, copy) UZURLTaskRedirectedBlock redirectedBlock;    // 重定向的回调

@property (readonly) int64_t countOfBytesSent;              // 已发送的数据大小
@property (readonly) int64_t countOfBytesExpectedToSend;    // 要发送的总数据大小
@property (readonly) int64_t countOfBytesReceived;          // 已接收的数据大小
@property (readonly) int64_t countOfBytesExpectedToReceive; // 要接收的总数据大小

- (void)start;      // 开始请求
- (void)cancel;     // 取消请求

@end


@interface UZURLDataTask : UZURLTask

@end


@interface UZURLDownloadTask : UZURLTask

@property (nonatomic, copy) NSString *storagePath;       // 存储文件夹
@property (nonatomic, copy) NSString *savePath;          // 存储路径，若不赋值将自动生成路径

@property BOOL allowResume;     // 是否允许断点续传

@end


typedef enum {
    // 默认缓存策略为 UZAskServerIfModifiedWhenStaleCachePolicy
    UZUseDefaultCachePolicy = 0,
    // 不从缓存读取
    UZDoNotReadFromCacheCachePolicy = 1,
    // 不写入缓存
    UZDoNotWriteToCacheCachePolicy = 2,
    // 当缓存过期后才询问服务器是否有更新
    UZAskServerIfModifiedWhenStaleCachePolicy = 4,
    // 总是询问服务器是否有更新
    UZAskServerIfModifiedCachePolicy = 8,
    // 无缓存数据时才进行请求
    UZOnlyLoadIfNotCachedCachePolicy = 16,
    // 无缓存数据时则停止请求
    UZDontLoadCachePolicy = 32,
    // 如果请求失败了则使用缓存
    UZFallbackToCacheIfLoadFailsCachePolicy = 64
} UZURLCachePolicy;

@interface UZURLRequest : NSObject

+ (instancetype)requestWithURL:(nonnull NSURL *)url;

@property (nonnull, readonly, strong) NSURL *url;       // 请求地址
@property (nullable, copy) NSString *HTTPMethod;        // 请求方法，如GET
@property (nullable, strong) NSDictionary *header;      // 请求头
@property (nullable, strong) NSData *body;              // 请求body
@property (nullable, copy) NSString *fileStream;        // stream方式提交文件的路径
@property (nullable, strong) NSDictionary *values;      // 以form表单形式提交数据，如@{@"name":@"xxx"}
@property (nullable, strong) NSDictionary *files;       // 以form表单形式提交文件，支持多文件上传，如@{@"file":@"path"}，也支持同一字段对应多文件：@{@"file":@[@"path1",@"path2"]}

@property BOOL useCache;                    // 是否使用缓存，默认NO
@property UZURLCachePolicy cachePolicy;     // 缓存策略

@property NSTimeInterval timeout;           // 请求超时时间，默认30s

@property BOOL allowsCellularAccess;        // 是否允许蜂窝数据进行请求，默认YES

@property (nullable, copy) NSString *SSLLevel;              // TLS版本，默认值为 kCFStreamSocketSecurityLevelNegotiatedSSL
@property (nullable, copy) NSString *certificatePath;       // 客户端HTTPS安全证书路径
@property (nullable, copy) NSString *certificatePassword;   // 客户端HTTPS安全证书密码
@property BOOL validatesServerCertificate;                  // 是否校验服务端自签名证书，默认为NO

@end


@interface UZURLResponse : NSObject

@property (readonly) NSInteger statusCode;                      // 响应状态码
@property (nullable, readonly, strong) NSDictionary *header;    // 响应头
@property (nullable, readonly, strong) NSData *data;            // 响应数据

@end

