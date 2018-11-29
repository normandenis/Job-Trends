package io.jobtrends.jobtrends.activities

interface State

interface ActivityListener {

    var state: State

    fun onNavNext()

    fun onNavBack()

    fun onSetState(state: State)

    fun onGetState(): State
}

