package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.mapper.Mapper;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public TeacherReadOnlyDTO insertTeacher(TeacherInsertDTO dto)
            throws TeacherDAOException, TeacherAlreadyExistsException {
        Teacher teacher;
        Teacher insertedTeacher;

        try {
            teacher = Mapper.mapTeacherInsertToModel(dto);
            if (teacherDAO.getTeacherByVat(dto.getVat()) != null)
                throw new TeacherAlreadyExistsException("Teacher with vat" + dto.getVat() + " exists");
            insertedTeacher = teacherDAO.insert(teacher);
            // logging
            return Mapper.mapTeacherToReadOnlyDTO(insertedTeacher).orElse(null);
        } catch (TeacherDAOException e) {
            //e.printStackTrace();
            // logging
            // rollback
            throw e;
        } catch (TeacherAlreadyExistsException e) {
            //e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO updateTeacher(Integer id, TeacherUpdateDTO dto)
            throws TeacherNotFoundException, TeacherAlreadyExistsException, TeacherDAOException {
        Teacher teacher;
        Teacher fetchedTeacher;

        try {
            if (teacherDAO.getById(id) == null)
                throw new TeacherNotFoundException("Teacher with id " + id + " was not found");


            fetchedTeacher = teacherDAO.getTeacherByVat(dto.getVat());
            if (fetchedTeacher != null && !fetchedTeacher.getId().equals(dto.getId())) {
                throw new TeacherAlreadyExistsException("Teacher with id: " + dto.getId() + " already exists");
            }
            teacher = Mapper.mapTeacherUpdateToModel(dto);
            Teacher updatedTeacher =  teacherDAO.update(teacher);
            // logging
            return Mapper.mapTeacherToReadOnlyDTO(updatedTeacher).orElse(null);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            //e.printStackTrace();
            // logging
            // rollback
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id)
            throws TeacherDAOException, TeacherNotFoundException {

        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher not found");
            }
            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            // e.printStackTrace();
            // rollback
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO getTeacherById(Integer id)
            throws TeacherNotFoundException, TeacherDAOException {
        Teacher teacher;

        try {
            teacher = teacherDAO.getById(id);
            return Mapper.mapTeacherToReadOnlyDTO(teacher).orElseThrow(() -> new TeacherNotFoundException("Teacher not found in get teacher by id"));
        } catch (TeacherNotFoundException | TeacherDAOException e) {
            //e.printStackTrace();
            // rollback
            throw e;
        }
    }

    @Override
    public List<TeacherReadOnlyDTO> getAllTeachers() throws TeacherDAOException {
        List<Teacher> teachers;

        try {
            teachers = teacherDAO.getAll();
            return teachers.stream()
                    .map(Mapper::mapTeacherToReadOnlyDTO)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<TeacherReadOnlyDTO> getTeachersByLastname(String lastname)
            throws TeacherDAOException {
        List<Teacher> teachers;

        try {
            teachers = teacherDAO.getByLastname(lastname);

            return teachers.stream()
                    .map(Mapper::mapTeacherToReadOnlyDTO)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
//            return teachers.stream()
//                    .map(teacher -> Mapper.mapTeacherToReadOnlyDTO(teacher).orElse(null))
//                    .collect(Collectors.toList());
        } catch (TeacherDAOException e) {
           // e.printStackTrace();
            throw e;
        }
    }
}
