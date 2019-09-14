fun log(str:String){
    println("Times: ${System.currentTimeMillis()} ThreadName：${Thread.currentThread().name} Result：$str")
}