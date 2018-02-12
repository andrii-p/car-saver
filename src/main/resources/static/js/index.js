import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { BrowserRouter } from 'react-router-dom'
import App from './components/App'
import data from '../data/cars'
import '../../../../../node_modules/bootstrap/dist/css/bootstrap.min.css';


window.React = React;

render(
    <App cars={data._embedded.cars} />,
    document.getElementById('react-container')
)
