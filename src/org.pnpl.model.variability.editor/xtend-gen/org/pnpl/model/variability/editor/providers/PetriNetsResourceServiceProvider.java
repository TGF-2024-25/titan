package org.pnpl.model.variability.editor.providers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.impl.SimpleResourceDescriptionsBasedContainerManager;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class PetriNetsResourceServiceProvider implements IResourceServiceProvider {
  private final SimpleResourceDescriptionsBasedContainerManager containerManager = new SimpleResourceDescriptionsBasedContainerManager();

  @Override
  public boolean canHandle(final URI uri) {
    return uri.fileExtension().equals("petrinets");
  }

  @Override
  public <T extends Object> T get(final Class<T> t) {
    return null;
  }

  @Override
  public IContainer.Manager getContainerManager() {
    return this.containerManager;
  }

  @Override
  public IEncodingProvider getEncodingProvider() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }

  @Override
  public IResourceDescription.Manager getResourceDescriptionManager() {
    return new IResourceDescription.Manager() {
      @Override
      public boolean isAffected(final Collection<IResourceDescription.Delta> deltas, final IResourceDescription candidate, final IResourceDescriptions context) throws IllegalArgumentException {
        return false;
      }

      @Override
      public boolean isAffected(final IResourceDescription.Delta delta, final IResourceDescription candidate) throws IllegalArgumentException {
        return false;
      }

      @Override
      public IResourceDescription.Delta createDelta(final IResourceDescription oldDescription, final IResourceDescription newDescription) {
        return new DefaultResourceDescriptionDelta(oldDescription, newDescription);
      }

      @Override
      public IResourceDescription getResourceDescription(final Resource resource) {
        abstract class ____PetriNetsResourceServiceProvider_1 implements IQualifiedNameProvider {
          public abstract QualifiedName name(final EObject obj);
        }

        DefaultResourceDescriptionStrategy strat = new DefaultResourceDescriptionStrategy();
        ____PetriNetsResourceServiceProvider_1 _____PetriNetsResourceServiceProvider_1 = new ____PetriNetsResourceServiceProvider_1() {
          public QualifiedName name(final EObject obj) {
            final Function1<EStructuralFeature, Boolean> _function = (EStructuralFeature it) -> {
              return Boolean.valueOf(it.getName().equals("name"));
            };
            EStructuralFeature fname = IterableExtensions.<EStructuralFeature>findFirst(obj.eClass().getEAllStructuralFeatures(), _function);
            if ((fname != null)) {
              return QualifiedName.create(obj.eGet(fname).toString());
            }
            return null;
          }

          @Override
          public QualifiedName apply(final EObject obj) {
            return this.name(obj);
          }

          @Override
          public QualifiedName getFullyQualifiedName(final EObject obj) {
            return this.name(obj);
          }
        };
        strat.setQualifiedNameProvider(_____PetriNetsResourceServiceProvider_1);
        return new DefaultResourceDescription(resource, strat);
      }
    };
  }

  @Override
  public IResourceValidator getResourceValidator() {
    return new IResourceValidator() {
      @Override
      public List<Issue> validate(final Resource resource, final CheckMode mode, final CancelIndicator indicator) {
        return Collections.<Issue>emptyList();
      }
    };
  }
}
