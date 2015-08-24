<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  	        <div class="form-group">
          <label for="name">name</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:input path='name'/>
<form:errors path='name'/>

          </div>
        </div>
        <div class="form-group">
          <label for="description">description</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:input path='description'/>
<form:errors path='description'/>

          </div>
        </div>
        <div class="form-group">
          <label for="price">price</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:input path='price'/>
<form:errors path='price'/>

          </div>
        </div>
        <div class="form-group">
          <label for="category.id">category.id</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:select path='category.id' items='${categories}' itemValue='id' itemLabel='name'>
</form:select>
<form:errors path='category.id'/>

          </div>
        </div>
