import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'
import App from './components/App'
import data from '../data/cars'

window.React = React;

render(
    <App cars={data._embedded.cars} />,
    document.getElementById('react-container')
)
