import {Component} from 'react'
import PropTypes from 'prop-types'
import Spinner from './Spinner'
import '../../../stylesheets/input-form-common.scss'

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
            <div className="input-form-common">
                <form>
                    <div className="form-group text-center">
                        <label>Process default RSS:</label>
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary center-block" onClick={this.submitDefault}>Submit</button>
                    </div>


                    <div className="form-group text-center">
                        <label>Process custom RSS:</label>
                        <input class="form-control" ref="_rss" type="text"/>
                    </div>
                    <button className="btn btn-primary center-block" onClick={this.submit}>Submit</button>
                </form>
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

PostForm.propTypes = {
    isCallingAPI: PropTypes.bool.isRequired,
    postRss: PropTypes.func.isRequired
}

PostForm.defaultProps = {
    isCallingAPI: false,
    postRss: () => {
    }
}

export default PostForm