package com.lxs.livedatabus

/**
 * @author liuxiaoshuai
 * @date 2019-07-21
 * @desc
 * @email liulingfeng@mistong.com
 */
class LiveDataBus private constructor() {
    private val bus = HashMap<String, MyMutableLiveData<Any>>()

    companion object {
        fun get(): LiveDataBus {
            return SingletonHolder.holder
        }
    }

    private object SingletonHolder {
        val holder = LiveDataBus()
    }

    public fun <T> with(target: String, type: Class<T>): MyMutableLiveData<T>? {
        if (!bus.containsKey(target)) {
            bus[target] = MyMutableLiveData()
        }
        return bus[target] as? MyMutableLiveData<T>
    }

    public fun with(target: String): MyMutableLiveData<Any>? {
        return with(target, Any::class.java)
    }
}