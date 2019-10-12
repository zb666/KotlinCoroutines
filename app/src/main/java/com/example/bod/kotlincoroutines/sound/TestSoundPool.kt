package com.example.bod.kotlincoroutines.sound

import com.example.bod.kotlincoroutines.R

/**
 * @ClassName: TestSoundPool
 * @Description:
 * @CreateDate: 2019/10/11
 */
class TestSoundPool : BaseSoundHelper() {
    override val soundResources: Map<Int, SoundResource>
        get() = mapOf(
                FOCUSPLANET_NEW_APPEAR to SoundResource(R.raw.focusplanet_newelement_appear, 1),
                FOCUSPLANET_COMMON_TREE to SoundResource(R.raw.focusplanet_tree, 1)
        )

    companion object {
        const val TAG = "FocusPlanetSoundHelper"
        //专注星球
        const val FOCUSPLANET_NEW_APPEAR = 1 //新物种出现
        const val FOCUSPLANET_COMMON_TREE = 2 //小树互动
        const val FOCUSPLANET_ENERGY = 3 //能量棒挥动音效
        const val FOCUSPLANET_GET_COIN = 4 //金币收入书包音效
        const val FOCUSPLANET_NEWELEMENT_DIALOG = 5 //新物种出现Dialog
        const val FOCUSPLANET_UFO = 6//ufo
    }
}
