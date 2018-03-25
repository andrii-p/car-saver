import {Component} from 'react'
import Spinner from './Spinner'

class PostForm extends Component {

    constructor(props) {
        super(props);
        this.submitDefault = this.submitDefault.bind(this);
        this.submit = this.submit.bind(this);
    }

    submitDefault(e) {
        e.preventDefault();
        this.props.postRss(" ");
    }

    submit(e) {
        e.preventDefault();
        const {_rss} = this.refs;
        this.props.postRss(_rss.value);
    }

    render() {
        const {isCallingAPI} = this.props;

        const page = (
            <div className="container">
                <div class="form-inline">
                    <div className="form-group">
                        <label>Process default RSS:</label>
                    </div>
                    <button className="btn btn-success" onClick={this.submitDefault}>Submit</button>
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
                <Spinner/>
                :
                page
        )
    }
}

export default PostForm