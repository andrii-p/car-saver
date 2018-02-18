import { Route, Switch } from 'react-router-dom'
import {CarsContainer} from './containers'
import Test from './ui/Test'
import Whoops404 from './ui/Whoops404'

const App = () =>
    <Switch>
        <Route exact path="/" component={CarsContainer}/>
        <Route path="/cars" component={Test}/>
        <Route component={Whoops404} />
    </Switch>

export default App
