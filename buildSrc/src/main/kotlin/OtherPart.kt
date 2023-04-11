object OtherPart {

    private const val vOkhttp = "4.10.0"
    private const val vRetrofit = "2.9.0"
    const val okhttp ="com.squareup.okhttp3:okhttp:$vOkhttp"
    const val okhttpLoggingInterceptor ="com.squareup.okhttp3:logging-interceptor:$vOkhttp"
    const val retrofit ="com.squareup.retrofit2:retrofit:$vRetrofit"
    const val retrofitConverterGson ="com.squareup.retrofit2:converter-gson:$vRetrofit"
//    implementation 'com.squareup.retrofit2:adapter-rxjava3:$vRetrofit'

    // 适配
    const val androidAutoSize = "com.github.JessYanCoding:AndroidAutoSize:v1.2.1"
    // dialog
    const val DialogX =  "com.kongzue.dialogx:DialogX:0.0.47"

    // 基础依赖包，必须要依赖
    private const val vImmersionBar = "3.2.2"
    const val immersionBar =   "com.geyifeng.immersionbar:immersionbar:$vImmersionBar"
    // kotlin扩展（可选）
    const val immersionBarKtx =   "com.geyifeng.immersionbar:immersionbar-ktx:$vImmersionBar"
    // fragment快速实现（可选）已废弃
//    implementation "com.geyifeng.immersionbar:immersionbar-components:$vImmersionBar"

    // adapt
    const val baseRecyclerViewAdapterHelper =  "io.github.cymchad:BaseRecyclerViewAdapterHelper:4.0.0-beta02"

    //    implementation("io.coil-kt:coil:2.2.2")
    private const val vGlide = "4.15.1"
    const val glide = "com.github.bumptech.glide:glide:$vGlide"
    const val glideCompiler =  "com.github.bumptech.glide:compiler:$vGlide"

    // 图片选择
    private const val vPictureSelector = "v3.10.8"
    const val pictureSelector = "io.github.lucksiege:pictureselector:$vPictureSelector"
    const val pictureSelectorCompress = "io.github.lucksiege:compress:$vPictureSelector"
    const val pictureSelectorUcrop = "io.github.lucksiege:ucrop:$vPictureSelector"
}