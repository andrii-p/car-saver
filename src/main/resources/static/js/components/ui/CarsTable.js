import PropTypes from 'prop-types'
import CarRow from './CarRow'
import Spinner from './Spinner'

const CarsTable = ({cars = [], isCallingAPI = false}) => {

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
            <Spinner/>
            :
            carsList
    )
}

CarsTable.propTypes = {
    cars: PropTypes.array,
    isCallingAPI: PropTypes.bool
}

export default CarsTable