import PropTypes from 'prop-types'
import CarRow from './CarRow'
import FontAwesomeIcon from '@fortawesome/react-fontawesome'
import faSpinner from '@fortawesome/fontawesome-free-solid/faSpinner'
import '../../../stylesheets/spinner.scss'

const CarsTable = ({cars = [], isCallingAPI = false}) => {

    const spinner = (
        <div className="spinner">
            <FontAwesomeIcon icon={faSpinner} pulse size="5x"/>
        </div>

    )

    const carsList = (
        <div>
            {(cars.length === 0) ?
                <p>Cars list is empty.</p>
                :
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Ad name</th>
                        <th>Price</th>
                        <th>Location</th>
                        <th>Odometer</th>
                        <th>Condition</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        cars.map((car, i) =>
                            <CarRow key={i} car={car}/>
                        )
                    }
                    </tbody>
                </table>
            }
        </div>
    )

    return (
        isCallingAPI ?
            spinner
            :
            carsList
    )
}

CarsTable.propTypes = {
    cars: PropTypes.array,
    isCallingAPI: PropTypes.bool
}

export default CarsTable