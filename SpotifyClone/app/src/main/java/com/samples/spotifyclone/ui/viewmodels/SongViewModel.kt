package com.samples.spotifyclone.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.spotifyclone.exoplayer.MusicService
import com.samples.spotifyclone.exoplayer.MusicServiceConnection
import com.samples.spotifyclone.exoplayer.currentPlaybackPosition
import com.samples.spotifyclone.util.Constants.UPDATE_PLAYER_POSITION_INTERVAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    musicServiceConnection: MusicServiceConnection
)  : ViewModel() {

    private val playbackState = musicServiceConnection.playbackState

    private val _currentSongDuration = MutableLiveData<Long>()
    val currentSongDuration : LiveData<Long> = _currentSongDuration

    private val _currentPlayerPosition = MutableLiveData<Long>()
    val currentPlayerPosition : LiveData<Long> = _currentPlayerPosition

    init {
        updateCurrentPlayerPosition()
    }

    private fun updateCurrentPlayerPosition(){
        viewModelScope.launch {
            while(true){
                val pos = playbackState.value?.currentPlaybackPosition
                if(currentPlayerPosition.value != pos){
                    _currentPlayerPosition.postValue(pos)
                    _currentSongDuration.postValue(MusicService.currSongDuration)
                }
                delay(UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }
}