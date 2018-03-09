import {Component} from 'react'
import FontAwesomeIcon from '@fortawesome/react-fontawesome'
import faSpinner from '@fortawesome/fontawesome-free-solid/faSpinner'
import '../../../stylesheets/spinner.scss'

class PostForm extends Component {

    constructor(props) {
        super(props);
        this.submitDefault = this.submitDefault.bind(this);
        this.submit = this.submit.bind(this);
    }

    submitDefault(e) {
        e.preventDefault();
        this.props.postRss();
    }

    submit(e) {
        e.preventDefault();
        const {_rss} = this.refs;
        this.props.postRss(_rss.value);
    }

    render() {
        const {isCallingAPI} = this.props;

        const spinner = (
            <div className="spinner">
                <FontAwesomeIcon icon={faSpinner} pulse size="5x"/>
            </div>

        )

        const page = (
            <div className="container">
                <div class="form-inline">
                    <div className="form-group">
                        <label>Process default RSS:</label>
                    </div>
                    <button className="btn btn-success">Submit</button>
                </div>

                <div>
                    <form class="form-inline" onSubmit={this.submit}>
                        <div className="form-group">
                            <label>Process custom RSS:</label>
                            <input class="form-control" ref="_rss" type="text"/>
                        </div>
                        <button className="btn btn-success">Submit</button>
                    </form>
                </div>
            </div>
        )

        return (
            isCallingAPI ?
                spinner
                :
                page
        )
    }
}

export default PostForm