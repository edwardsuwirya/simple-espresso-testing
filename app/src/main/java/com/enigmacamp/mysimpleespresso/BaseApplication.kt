package com.enigmacamp.mysimpleespresso

import android.app.Application
import com.enigmacamp.mysimpleespresso.data.SpentDatabase

class BaseApplication : Application() {
    val spentDatabase = SpentDatabase()

}