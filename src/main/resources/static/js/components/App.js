import {Route, Switch} from 'react-router-dom'
import {CarContainer, HomeContainer, MenuContainer, PostFormContainer} from './containers'
import Whoops404 from './ui/Whoops404'
import '../../stylesheets/app.scss'

const App = () =>
    <div className="app">
        <Route component={MenuContainer}/>
        <Switch>
            <Route exact path="/" component={HomeContainer}/>
            <Route exact path="/cars/:id" component={CarContainer}/>
            <Route exact path="/postRss" component={PostFormContainer}/>
            <Route component={Whoops404}/>
        </Switch>
    </div>

export default App
