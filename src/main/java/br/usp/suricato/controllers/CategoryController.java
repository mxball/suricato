package br.usp.suricato.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.usp.suricato.models.Category;
import br.usp.suricato.daos.CategoryDao;

@Controller
@RequestMapping("/categories")
@Transactional
public class CategoryController
{

   @Autowired
   private CategoryDao categoryDao;

   @RequestMapping("/form")
   public ModelAndView form(Category category)
   {
      ModelAndView modelAndView = new ModelAndView("categories/form-add");
      return modelAndView;

   }

   @RequestMapping(method = RequestMethod.POST)
   public ModelAndView save(@Valid Category category, BindingResult bindingResult)
   {
      if (bindingResult.hasErrors())
      {
         return form(category);
      }
      categoryDao.save(category);
      return new ModelAndView("redirect:/categories");
   }

   @RequestMapping(method = RequestMethod.GET, value = "/{id}")
   public ModelAndView load(@PathVariable("id") Integer id)
   {
      ModelAndView modelAndView = new ModelAndView("categories/form-update");
      modelAndView.addObject("category", categoryDao.findById(id));
      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET)
   public ModelAndView list()
   {
      ModelAndView modelAndView = new ModelAndView("categories/list");
      modelAndView.addObject("list", categoryDao.all());
      return modelAndView;
   }

   //just because get is easier here. Be my guest if you want to change.
   @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
   public String remove(@PathVariable("id") Integer id)
   {
      Category category = categoryDao.findById(id);
      categoryDao.remove(category);
      return "redirect:/categories";
   }

   @RequestMapping(method = RequestMethod.POST, value = "/{id}")
   public ModelAndView update(@PathVariable("id") Integer id, @Valid Category category, BindingResult bindingResult)
   {
      category.setId(id);
      if (bindingResult.hasErrors())
      {
         return new ModelAndView("categories/form-update");
      }
      categoryDao.update(category);
      return new ModelAndView("redirect:/categories");
   }
}
