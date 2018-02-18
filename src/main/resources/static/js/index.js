import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { HashRouter } from 'react-router-dom'
import App from './components/App'
import '../../../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import storeFactory from './store'

const store = storeFactory()

window.React = React
window.store = store

render(
    <Provider store={store}>
        <HashRouter>
            <App />
        </HashRouter>
    </Provider>,
    document.getElementById('react-container')
)
