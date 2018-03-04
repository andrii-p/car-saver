import { Route, Switch } from 'react-router-dom'
import {CarsContainer} from './containers'
import Menu from "./ui/Menu";
import Test from './ui/Test'
import Whoops404 from './ui/Whoops404'
import '../../stylesheets/app.scss'

const App = () =>
    <div className="app">
        <Route component={Menu} />
        <Switch>
            <Route exact path="/" component={CarsContainer}/>
            <Route path="/cars" component={Test}/>
            <Route component={Whoops404} />
        </Switch>
    </div>

export default App
