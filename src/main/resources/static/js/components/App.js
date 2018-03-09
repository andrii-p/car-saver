import { Route, Switch } from 'react-router-dom'
import {CarsContainer, CarContainer, PostFormContainer} from './containers'
import Menu from "./ui/Menu";
import Whoops404 from './ui/Whoops404'
import '../../stylesheets/app.scss'

const App = () =>
    <div className="app">
        <Route component={Menu} />
        <Switch>
            <Route exact path="/" component={CarsContainer}/>
            <Route exact path="/cars/:id" component={CarContainer}/>
            <Route exact path="/postRss" component={PostFormContainer}/>
            <Route component={Whoops404} />
        </Switch>
    </div>

export default App
