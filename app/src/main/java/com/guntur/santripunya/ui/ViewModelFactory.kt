package com.guntur.santripunya.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guntur.santripunya.data.Repository
import com.guntur.santripunya.ui.screen.alquran.QuranViewModel
import com.guntur.santripunya.ui.screen.asmaulhusna.AsmaViewModel
import com.guntur.santripunya.ui.screen.doaharian.DoaHarianViewModel
import com.guntur.santripunya.ui.screen.kitab.KitabViewModel
import com.guntur.santripunya.ui.screen.tahlil.TahlilViewModel
import com.guntur.santripunya.ui.screen.wirid.WiridViewModel
import com.guntur.santripunya.ui.screen.yaasin.YasiinViewModel


class ViewModelFactory(private val repos: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WiridViewModel::class.java)){
            return WiridViewModel(repos) as T
        }
        else if (modelClass.isAssignableFrom(KitabViewModel::class.java)){
            return KitabViewModel(repos) as T
        }
        else if (modelClass.isAssignableFrom(DoaHarianViewModel::class.java)){
            return DoaHarianViewModel(repos) as T
        }
        else if (modelClass.isAssignableFrom(AsmaViewModel::class.java)){
            return AsmaViewModel(repos) as T
        }
        else if (modelClass.isAssignableFrom(YasiinViewModel::class.java)){
            return YasiinViewModel(repos) as T
        }
        else if (modelClass.isAssignableFrom(QuranViewModel::class.java)){
            return QuranViewModel(repos) as T
        }
        else if (modelClass.isAssignableFrom(TahlilViewModel::class.java)){
            return TahlilViewModel(repos) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }
}