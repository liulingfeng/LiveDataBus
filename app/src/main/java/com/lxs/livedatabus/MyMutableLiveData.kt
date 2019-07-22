package com.lxs.livedatabus

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * @author liuxiaoshuai
 * @date 2019-07-22
 * @desc hook解决
 * 保证先订阅后执行
 * @email liulingfeng@mistong.com
 */
class MyMutableLiveData<T> : MutableLiveData<T>() {
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, observer)

        try {
            hook(observer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun hook(observer: Observer<T>) {
        val classLiveData = LiveData::class.java
        val filedObservers = classLiveData.getDeclaredField("mObservers")
        filedObservers.isAccessible = true
        val mObservers = filedObservers.get(this)
        val classObserver = mObservers::class.java
        val methodGet = classObserver.getDeclaredMethod("get", Any::class.java)
        methodGet.isAccessible = true
        val objectWrapperEntry = methodGet.invoke(mObservers, observer)
        val observerWrapper = (objectWrapperEntry as Map.Entry<*, *>).value
        val classObserverWrapper = observerWrapper?.javaClass?.superclass

        val filedLastVersion = classObserverWrapper?.getDeclaredField("mLastVersion")
        filedLastVersion?.isAccessible = true
        val filedVersion = classLiveData.getDeclaredField("mVersion")
        filedVersion.isAccessible = true
        val objectValue = filedVersion.get(this)
        filedLastVersion?.set(observerWrapper, objectValue)
    }
}