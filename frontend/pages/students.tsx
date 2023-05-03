import {useEffect, useState} from "react";

interface Student {
    id: string;
    name: string;
    age: number;
    gender: number;
    createdAt: Date;
    updatedAt: Date;
}

const Students = () => {

    let [students, setStudents] = useState<Student[] | null>(null);
    let [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true)

        fetch('/api/students', {
            method: 'POST'
        })
            .then(resp => resp.json())
            .then(resp => {
                setStudents(() => resp.data)
                setLoading(false)
            })
    }, [])

    return (
        <main>
            <ul>
                {loading ? (<div>loading</div>) : students?.map((student, i) => {
                    return (
                        <li key={i}>
                            {`${student.id} | ${student.name} | ${student.age} | ${student.gender === 1 ? '男' : '女'} | ${student.createdAt} | ${student.updatedAt}`}
                        </li>
                    )
                })}
            </ul>
        </main>
    )
}

export default Students
