
# AndroidLog
> 一个适应于Java/Kotin项目日志打印工具库，同时支持在安卓和PC机使用，同时也支持跨进程收集数据


![ffa0f3de8a35e0abde2b58dca00b1e27.png](evernotecid://188EB108-328C-4849-AB56-80E65BFFBB10/appyinxiangcom/23472239/ENResource/p14829)
[项目地址](https://github.com/worson/AwesomeLog)

基础功能有：
- 指定日志级别过滤日志
- 打印日志调用栈信息
- 输出日志到指定文件中

高级功能有：
- 支持跨进程收集数据
- 支持日志加密压缩
- 支持日志上报到相关的日志平台

# 导入库到项目

根目录gradle文件下配置
```
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
```
应用目录gradle文件下配置依赖
```
implementation 'com.github.worson:AwesomeLog:0.1'
```

# 使用方法

## 初始化控制台输出日志
此方法会自适应运行平台
```
fun initConsolePrint(debug:Boolean){
        L.init(
            LogConfiguration.Builder()
                .logLevel(if (debug) LogLevel.ALL else LogLevel.DEBUG)
                .threadInfo(debug)
                .traceInfo(debug, 7)
                .addPrinter(Platform.get().defaultPrinter())
                .build()
        )
    }
```

## 初始化文件输出日志

```
fun initFileLog(context: Context,debug:Boolean=true) {
        var filePrinter: Printer?=null
        var logHandler: ZipLogHandler?=null
        var logUploader: LogFileReporter?=null
        val logPath = File(context.cacheDir,"log")
        L.init(
            LogConfiguration.Builder()
                .logLevel(if (debug) LogLevel.ALL else LogLevel.DEBUG)
                .threadInfo(true)
                .traceInfo(debug)
                .addPrinter(Platform.get().defaultPrinter())
                .addPrinter(
                    FilePrinter.Builder(
                        File(logPath.absolutePath, "logging").absolutePath
                    )
                        .fileNameGenerator(DateFileNameGenerator())
                        .logHandler(
                            ZipLogHandler(
                                File(logPath.absolutePath, "backup").absolutePath,
                                limitSize = 100 * 1024 * 1024,
                                reporter = logUploader
                            ).apply {
                                logHandler=this
                            }
                        )
                        .build().apply {
                            filePrinter = this
                        }
                )
                .build()
        )
        filePrinter?.let {
            SocketSeverPrinterProxy(it).start()
        }
    }
```

## 打印日志
```
L.i(TAG, "onCreate: ")
```
打印日志，lamda方法在过滤日志可减少性能消耗
```
L.d(TAG) {"onCreate: "}
```