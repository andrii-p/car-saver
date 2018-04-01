import PropTypes from 'prop-types'
import CarRow from './CarRow'

const CarsTable = ({cars = []}) =>
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

CarsTable.propTypes = {
    cars: PropTypes.array
}

export default CarsTable