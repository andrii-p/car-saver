import PropTypes from 'prop-types'
import CarRow from './CarRow'

const CarsTable = ({cars = []}) =>
    <div>
        {(cars.length === 0) ?
            <p>Cars list is empty.</p> :
            <table className="table">
                <thead>
                <tr>
                    <th>Ad name</th>
                    <th>Price</th>
                    <th>Location</th>
                    <th>Odometer</th>
                    <th>Condition</th>
                </tr>
                </thead>
                <tbody>
                cars.map( (car, i) =>
                    <CarRow key={id} car={car} />
                )
                </tbody>
            </table>
        }
    </div>

CarsTable.propTypes = {
    cars: PropTypes.array
}

export default CarsTable